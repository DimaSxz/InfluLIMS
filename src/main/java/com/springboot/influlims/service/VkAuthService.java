package com.springboot.influlims.service;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.exceptions.OAuthException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.UserAuthResponse;
import com.vk.api.sdk.objects.users.UserXtrCounters;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
@PropertySource("classpath:/social.properties")
public class VkAuthService {

	@Value("${vk.network-name}")
	private String networkName;

	@Value("${vk.client-id}")
	private Integer clientId;

	@Value("${vk.client-secret}")
	private String clientSecret;

	@Value("${vk.redirect-uri}")
	private String redirectURI;

	@Value("${vk.display}")
	private String display;

	@Value("${vk.scope}")
	private String scope;

	@Value("${vk.response_type}")
	private String responseType;

	@Value("${vk.v}")
	private String version;

	@Value("${vk.state}")
	private String state;

	private boolean authorized = false;

	private VkApiClient vk;
	private String accessToken = null;
	private UserActor actor;
	private UserAuthResponse authResponse;
	private UserXtrCounters userXtrCounter = null;

	public String getAuthUrl() {
		return "https://oauth.vk.com/authorize?client_id=" + clientId + "&redirect_uri=" + redirectURI + "&display=" + display + "&scope=" + scope + "&response_type=" + responseType + "&v=" + version + "&state=" + state;
	}

	public void authorize(String code) {
		if(!authorized) {
			TransportClient transportClient = HttpTransportClient.getInstance();
			vk = new VkApiClient(transportClient);
			try {
				authResponse = vk.oauth()
						.userAuthorizationCodeFlow(clientId, clientSecret, redirectURI, code)
						.execute();
				accessToken = authResponse.getAccessToken();
				actor = new UserActor(authResponse.getUserId(), accessToken);
				authorized = true;
			} catch (OAuthException e) {
				e.getRedirectUri();
			} catch (ApiException | ClientException e) {
				e.printStackTrace();
			}
		}
	}

	public String getNetworkName() {
		return networkName;
	}

	public String getEmail() {
		return (authorized) ? authResponse.getEmail() : null;
	}

	public String getFullName() {
		if(userXtrCounter == null) fetchUsersData();
		return (userXtrCounter == null) ? null : userXtrCounter.getFirstName() + " " + userXtrCounter.getLastName();
	}

	public String getPhone() {
		if(userXtrCounter == null) fetchUsersData();
		return (userXtrCounter == null) ? null : userXtrCounter.getMobilePhone();
	}

	public String getAccessToken() {
		return accessToken;
	}

	private void fetchUsersData() {
		if(!authorized) return;
		try {
			userXtrCounter = vk.users().get(actor).execute().get(0);
		} catch (ApiException | ClientException e) {
			e.printStackTrace();
		}
	}

}
