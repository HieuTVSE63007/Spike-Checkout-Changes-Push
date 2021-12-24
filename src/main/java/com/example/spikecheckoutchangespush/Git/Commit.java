package com.example.spikecheckoutchangespush.Git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

public class Commit {

    public static void main(String[] args) {
        //get existing repo
        try(Git git = Git.open(new File("D:/IntelliJ/Project/Spring-boot"))){
            Repository repository = git.getRepository();

//            git.checkout().setName("test").call();

            git.add().addFilepattern(".").call();
            git.commit().setMessage("New commit").call();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoFilepatternException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

}
