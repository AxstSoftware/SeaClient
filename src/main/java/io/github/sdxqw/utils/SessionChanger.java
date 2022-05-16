package io.github.sdxqw.utils;

import com.mojang.authlib.Agent;
import com.mojang.authlib.AuthenticationService;
import com.mojang.authlib.UserAuthentication;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.util.UUIDTypeAdapter;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import io.github.sdxqw.mixins.interfaces.IMinecraftMixin;
import io.github.sdxqw.utils.interfaces.IHelper;
import net.minecraft.util.Session;

import java.util.UUID;

public class SessionChanger {
    private static SessionChanger instance;
    private final UserAuthentication auth;

    public static SessionChanger getInstance() {
        if (instance == null) {
            instance = new SessionChanger();
        }

        return instance;
    }

    private SessionChanger() {
        UUID notSureWhyINeedThis = UUID.randomUUID();
        AuthenticationService authService = new YggdrasilAuthenticationService(IHelper.minecraft.getProxy(), notSureWhyINeedThis.toString());
        auth = authService.createUserAuthentication( Agent.MINECRAFT);
        authService.createMinecraftSessionService();
    }

    public void setUser(String email, String password) {
        if(!IHelper.minecraft.getSession().getUsername().equals(email) || IHelper.minecraft.getSession().getToken().equals("0")){

            this.auth.logOut();
            this.auth.setUsername(email);
            this.auth.setPassword(password);
            try {
                this.auth.logIn();
                Session session = new Session(this.auth.getSelectedProfile().getName(), UUIDTypeAdapter.fromUUID(auth.getSelectedProfile().getId()), this.auth.getAuthenticatedToken(), this.auth.getUserType().getName());
                setSession(session);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void setUserMicrosoft(String email, String password) {

        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
        try {
            MicrosoftAuthResult acc = authenticator.loginWithCredentials(email, password);

            Session session = new Session(acc.getProfile().getName(), acc.getProfile().getId(), acc.getAccessToken(), "legacy");

        } catch (MicrosoftAuthenticationException e) {
            throw new RuntimeException( e );
        }
    }

    public void setSession(Session session) {
        ((IMinecraftMixin) IHelper.minecraft).setSession(session);
    }

    public void setUserOffline(String username) {
        auth.logOut();
        Session session = new Session(username, username, "0", "legacy");
        setSession(session);
    }
}
