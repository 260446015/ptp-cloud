package com.yonyougov.oauth.entity;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.ClientDetails;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
@Entity
@Table(name = "oauth_client_details")
@Getter
@Setter
public class OauthClientDetails implements ClientDetails {

    @Id
    @Column(unique = true,length = 48)
    private String clientId;
    private String resourceIds;
    private String clientSecret;
    private String scope;
    private String authorizedGrantTypes;
    private String webServerRedirectUri;
    private String authorities;
    private Integer accessTokenValidity;
    private Integer refreshTokenValidity;
    @Column(length = 4096)
    private String additionalInformation;
    @Column
    private String autoapprove;
    private String loadClientByClientId;
    @Column
    private Boolean secretRequired;
    @Column
    private Boolean scoped;

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public Set<String> getResourceIds() {
        return Stream.of(this.resourceIds.split(","))
                .collect(Collectors.toSet());
    }

    @Override
    public boolean isSecretRequired() {
        return this.secretRequired;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public boolean isScoped() {
        return this.scoped;
    }

    @Override
    public Set<String> getScope() {
        return Stream.of(scope.split(","))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return Stream.of(this.webServerRedirectUri.split(","))
                .collect(Collectors.toSet());
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValidity;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValidity;
    }

    @Override
    public boolean isAutoApprove(String scope) {
        return true;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        Map<String, Object> map = new HashMap<>();
        if (StringUtils.isNotBlank(additionalInformation)) {
            String[] infos = additionalInformation.split(",");
            for (String info : infos) {
                String[] pairs = info.split("=");
                map.put(pairs[0], pairs[1]);
            }
        }
        return map;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return Stream.of(authorizedGrantTypes.split(","))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        if (org.springframework.util.StringUtils.hasText(authorities)) {
            return AuthorityUtils
                    .commaSeparatedStringToAuthorityList(authorities);
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}
