package com.yonyougov.oauth.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.*;

/***
 * -- ----------------------------
 * -- Table structure for oauth_client_details 将请求的路径存在数据表
 * -- ----------------------------
 * DROP TABLE IF EXISTS `oauth_client_details`;
 * CREATE TABLE `oauth_client_details` (
 *   `client_id` varchar(48) NOT NULL,
 *   `resource_ids` varchar(256) DEFAULT NULL,
 *   `client_secret` varchar(256) DEFAULT NULL,
 *   `scope` varchar(256) DEFAULT NULL,
 *   `authorized_grant_types` varchar(256) DEFAULT NULL,
 *   `web_server_redirect_uri` varchar(256) DEFAULT NULL,
 *   `authorities` varchar(256) DEFAULT NULL,
 *   `access_token_validity` int(11) DEFAULT NULL,
 *   `refresh_token_validity` int(11) DEFAULT NULL,
 *   `additional_information` varchar(4096) DEFAULT NULL,
 *   `autoapprove` varchar(256) DEFAULT NULL,
 *   PRIMARY KEY (`client_id`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
 * 所有加s的字段逗号分隔
 */
//@Entity
@Getter
@Setter
@Table(name = "oauth_client_details")
public class OauthClientDetails implements ClientDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(unique = true)
    private String clientId;
    @Column
    private String resources;
    @Column
    private Boolean secretRequired;
    @Column
    private String clientSecret;
    @Column
    private Boolean scoped;
    @Column
    private String scopes;
    @Column
    private String grantTypes;
    @Column
    private String registeredRedirectUris;
    @Column
    private String grantedAuthorities;
    @Column
    private Integer accessTokenValiditySeconds;
    @Column
    private Integer refreshTokenValiditySeconds;
    @Column
    private String additionalInformations;
    private String loadClientByClientId;

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        if (StringUtils.isNotBlank(resources)) {
            return new HashSet<>(Arrays.asList(resources.split(",")));
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public boolean isSecretRequired() {
        return secretRequired;
    }

    @Override
    public String getClientSecret() {
        return clientSecret;
    }

    @Override
    public boolean isScoped() {
        return scoped;
    }

    @Override
    public Set<String> getScope() {
        if (StringUtils.isNotBlank(scopes)) {
            return new HashSet<>(Arrays.asList(scopes.split(",")));
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        if (StringUtils.isNotBlank(grantTypes)) {
            return new HashSet<>(Arrays.asList(grantTypes.split(",")));
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        if (StringUtils.isNotBlank(registeredRedirectUris)) {
            return new HashSet<>(Arrays.asList(registeredRedirectUris.split(",")));
        } else {
            return Collections.emptySet();
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (org.springframework.util.StringUtils.hasText(grantedAuthorities)) {
            return AuthorityUtils
                    .commaSeparatedStringToAuthorityList(grantedAuthorities);
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(additionalInformations)) {
            String[] infos = additionalInformations.split(",");
            for (String info : infos) {
                String[] pairs = info.split("=");
                map.put(pairs[0], pairs[1]);
            }
        }
        return map;
    }
}
