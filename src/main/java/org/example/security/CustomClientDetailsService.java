package org.example.security;

import org.example.enity.OauthClientDetails;
import org.example.repository.OauthClientDetailsRepository;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomClientDetailsService implements org.springframework.security.oauth2.provider.ClientDetailsService {

    private final OauthClientDetailsRepository repo;

    public CustomClientDetailsService(OauthClientDetailsRepository repo) {
        this.repo = repo;
    }

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        OauthClientDetails client = repo.findById(clientId)
                .orElseThrow(() -> new ClientRegistrationException("Client not found"));
        BaseClientDetails details = new BaseClientDetails();
        details.setClientId(client.getClientId());
        details.setClientSecret(client.getClientSecret());
        details.setScope(Arrays.asList(client.getScope().split(",")));
        details.setAuthorizedGrantTypes(Arrays.asList(client.getAuthorizedGrantTypes().split(",")));
        details.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        details.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(client.getAuthorities()));
        return details;
    }
}