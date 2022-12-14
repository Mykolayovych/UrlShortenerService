package com.example.urlshortenerservice.shortUrlService;

import com.example.urlshortenerservice.shortUrlService.model.ShortUrl;
import com.example.urlshortenerservice.userService.model.User;
import com.example.urlshortenerservice.util.CodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ShortUrlService {

    @Autowired
    private CodeGenerator codeGenerator;

    @Autowired
    private ShortUrlRepository repository;

    public String createShortUrl(String original, User user) {
        if (original == null) {
            return null;
        }
        ShortUrl shortUrlByOriginal = findByOriginalAndUser(original, user);

        if (shortUrlByOriginal != null) {
            return shortUrlByOriginal.getHash();
        }
        int shorterLength = 6;
        String hash = codeGenerator.generate(shorterLength);
        ShortUrl shortUrl = ShortUrl.create(hash, original, user);
        repository.save(shortUrl);
        return shortUrl.getHash();
    }

    public ShortUrl findByOriginalAndUser(String original, User user) {
        return repository.findByOriginalUrlAndUser(original, user);
    }

    public ShortUrl findByHash(String hash) {
        return repository.findByHash(hash);
    }

    @Transactional
    public void deleteByHash(String hash) {
        repository.deleteByHash(hash);
    }

    public List<ShortUrl> getAllByLogin(String login) {
        return repository.findAllByUserLogin(login);
    }
}
