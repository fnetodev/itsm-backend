package br.com.itsm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.itsm.domain.Pessoa;
import br.com.itsm.repository.PessoaRepository;
import br.com.itsm.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PessoaRepository reposistory;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		Optional<Pessoa> user = reposistory.findByLogin(login);
		if(user.isPresent()) {
			return new UserSS(user.get().getId(), user.get().getLogin(),user.get().getSenha(), user.get().getPerfis());
		}
		throw new UsernameNotFoundException(login);
		
	}

}
