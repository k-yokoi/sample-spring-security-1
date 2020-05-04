package org.debugroom.sample.spring.security.chat.app.web.security;

import org.debugroom.sample.spring.security.chat.domain.repository.portal.PortalUserResourceRepository;
import org.debugroom.sample.spring.security.common.apinfra.exception.BusinessException;
import org.debugroom.sample.spring.security.common.model.user.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    MessageSource messageSource;

    @Autowired
    PortalUserResourceRepository portalUserResourceRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        try {
            UserResource userResource = portalUserResourceRepository.findOneByLoginId(userName);
            List<GrantedAuthority> authorities = null;
            if(userResource.isAdmin()){
                authorities = AuthorityUtils.createAuthorityList( "ROLE_ADMIN", "ROLE_USER" );
            }else{
                authorities = AuthorityUtils.createAuthorityList( "ROLE_USER" );
            }
            return CustomUserDetails.builder()
                    .userResource(userResource)
                    .authorities(authorities)
                    .build();
        }catch (BusinessException e){
            throw new UsernameNotFoundException(messageSource.getMessage(
                    "BE0001", null, Locale.getDefault()), e);
        }
    }

}
