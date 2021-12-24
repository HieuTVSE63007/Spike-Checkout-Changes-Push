package com.example.spikecheckoutchangespush.Git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

public class Push {
    public static void main(String[] args) throws IOException {
        CredentialsProvider cp = new UsernamePasswordCredentialsProvider(
                "truongvanhieu291014",
                "767yitwaqtandahekql3d2fm4dc2fktiyn5quurdgyfsuvjobtha");
        //get existing repo
        Git git = Git.open(new File("D:/IntelliJ/Project/Spring-boot"));
        try{
            Repository repository = git.getRepository();
            git.push()
                    .setCredentialsProvider(cp)
                    .call();

        } catch (NoFilepatternException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        } finally {
            git.close();
        }
    }
}
