package ru.novemis.http;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.novemis.util.HiddenProperties;

import javax.annotation.PostConstruct;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Random;
import java.util.stream.Collectors;

@Component
public class VkRequests {

    @Autowired
    private HiddenProperties hiddenProperties;
    @Autowired
    private HttpConnector httpConnector;

    @Value("${vk.apiVersion}")
    private String version = "5.103";
    private String token;

    @PostConstruct
    public void init() {
        token = hiddenProperties.getGroupSecret();
    }

    public void send(int peerId, String message) throws Throwable {
        String url = buildUrl("messages.send", new LinkedHashMap<String, String>() {{
            put("random_id", "" + new Random().nextInt());
            put("peer_id", "" + peerId);
            put("message", message);
        }});

        httpConnector.get(url);
    }

    public void getConversations() throws Throwable {
        String url = buildUrl("messages.getConversations", new LinkedHashMap<>());
        httpConnector.get(url);
    }

    public void removeChatUser(int chatId, int userId) throws Throwable {
        String url = buildUrl("messages.removeChatUser", new LinkedHashMap<String, String>() {{
            put("user_id", "" + userId);
            put("chat_id", "" + chatId);
        }});

        httpConnector.get(url);
    }

    private String buildUrl(String methodName, LinkedHashMap<String, String> params) {
        params.put("v", version);
        params.put("access_token", token);
        String paramsJoined = params.entrySet().stream()
          .map(entry -> URLEncoder.encode(entry.getKey()) + "=" + URLEncoder.encode(entry.getValue()))
          .collect(Collectors.joining("&"));



        String rs = "https://api.vk.com/method/" + methodName + "?" + paramsJoined;

        return rs;
    }

}
