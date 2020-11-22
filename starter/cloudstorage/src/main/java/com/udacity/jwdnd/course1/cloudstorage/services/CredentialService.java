package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CredentialService {

    private CredentialMapper credentialMapper;
    private EncryptionService encryptionService;

    public CredentialService(CredentialMapper credentialMapper, EncryptionService encryptionService) {
        this.credentialMapper = credentialMapper;
        this.encryptionService = encryptionService;
    }

    public List<Credential> getCredentials(Integer userId) {
        List<Credential> credentials = credentialMapper.getCredentials(userId);

        return credentials.stream().map(credential -> {
            String encodedKey = credential.getKey();
            String encryptedPassword = credential.getPassword();
            String decryptedPassword = encryptionService.decryptValue(encryptedPassword, encodedKey);
            credential.setDecryptedPassword(decryptedPassword);
            return credential;
        }).collect(Collectors.toList());
    }

    public int createOrUpdateNote(Credential credential, Integer userid, Integer credentialid) {
        credential.setUserid(userid);

        SecureRandom random = new SecureRandom();
        byte[] key = new byte[16];
        random.nextBytes(key);
        String encodedKey = Base64.getEncoder().encodeToString(key);
        String encryptedPassword = encryptionService.encryptValue(credential.getPassword(), encodedKey);
        credential.setPassword(encryptedPassword);
        credential.setKey(encodedKey);

        if(credentialid != null) {
            return credentialMapper.update(credential);
        }

        return credentialMapper.insert(credential);
    }

    public int deleteCredential(Credential credential, Integer userid) {
        credential.setUserid(userid);
        return credentialMapper.delete(credential);
    }
}