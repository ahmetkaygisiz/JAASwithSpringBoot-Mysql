package com.akua.loginmodule;

import java.io.IOException;
import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import com.akua.dao.UserDAO;
import com.akua.model.AppUser;

@Service
public class CustomLoginModule implements LoginModule, ApplicationContextAware {

	private String userName;
	private String password;
	private Subject subject;

	private static ApplicationContext context;

	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {

		this.subject = subject;
		NameCallback nameCallback = new NameCallback("username");
		PasswordCallback passwordCallback = new PasswordCallback("password:", false);

		try {
			callbackHandler.handle(new Callback[] { nameCallback, passwordCallback });
		} catch (IOException | UnsupportedCallbackException e) {
			e.printStackTrace();
		}

		userName = nameCallback.getName();
		password = new String(passwordCallback.getPassword());
	}

	@Override
	public boolean login() throws LoginException {
		AppUser user = null;
		try {
			user = context.getBean(UserDAO.class).getUser(userName);

			if (userName == null || (userName.equalsIgnoreCase(""))) {
				throw new LoginException(userName + " not found.");
			} else if (user.getUserName().equals(userName)) {
				if (user.getEncrytedPassword().equals(password)) {
					subject.getPrincipals().add(new UsernamePrincipal(userName));
					return true;
				}
			} else
				return false;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean commit() throws LoginException {
		return true;
	}

	@Override
	public boolean abort() throws LoginException {
		return false;
	}

	@Override
	public boolean logout() throws LoginException {
		return false;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		context = applicationContext;
	}

	public static <T> T getBean(Class<T> beanClass) {
		return context.getBean(beanClass);
	}
}