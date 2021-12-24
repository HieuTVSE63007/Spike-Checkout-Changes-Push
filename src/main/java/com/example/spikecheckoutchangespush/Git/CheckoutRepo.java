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
                "767yitwaqtandahekql3d2fm4dc2fktiyn5quurdgyfsuvjobtha");

        Git.cloneRepository()
                .setURI("https://truongvanhieu291014@dev.azure.com/truongvanhieu291014/truongvanhieu291014/_git/Spike-Checkout-Change-Push")
                .setDirectory(new File("D:/IntelliJ/Project/Spring-boot"))
                .setCredentialsProvider(cp)
                .call();
    }
}
