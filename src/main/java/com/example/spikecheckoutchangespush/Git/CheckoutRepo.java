package com.example.spikecheckoutchangespush.Git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

public class CheckoutRepo {
    public static void main(String[] args) throws IOException, GitAPIException {
        CredentialsProvider cp = new UsernamePasswordCredentialsProvider(
                "truongvanhieu291014",
                "c3gvoxnqyv6zotjv3lkbjasz3zmuom7rt7b4gxqof35vgcypo7ka");
        File file = new File("D:/IntelliJ/Project/Spring-boot-1");
        if (file.exists()) {
            System.out.println("Folder is existed !");
        } else {
            Git.cloneRepository()
                    .setURI("https://truongvanhieu291014@dev.azure.com/truongvanhieu291014/truongvanhieu291014/_git/Spike-Checkout-Change-Push")
                    .setBranch("main")
                    .setDirectory(file)
                    .setCredentialsProvider(cp)
                    .call();
        }
    }
}
