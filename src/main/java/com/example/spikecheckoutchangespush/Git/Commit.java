package com.example.spikecheckoutchangespush.Git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Commit {

    public static void main(String[] args) throws IOException {
        //get existing repo
        String gitFileDir = "D:/IntelliJ/Project/Spring-boot";
        Git git = Git.open(new File(gitFileDir));
        try{
            Repository repository = git.getRepository();

            git.add().addFilepattern(".").call();
            git.commit().setMessage("New commit").call();

//            GitCommand.pushToRepo(
//                    "truongvanhieu291014",
//                    "767yitwaqtandahekql3d2fm4dc2fktiyn5quurdgyfsuvjobtha",
//                    gitFileDir
//            );

        } catch (NoFilepatternException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        } finally {
            git.close();
        }
    }

}
