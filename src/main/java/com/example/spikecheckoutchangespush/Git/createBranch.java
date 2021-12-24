package com.example.spikecheckoutchangespush.Git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;

import java.io.File;
import java.io.IOException;

public class createBranch {
    public static void main(String[] args) {
        //get existing repo
        try(Git git = Git.open(new File("D:/IntelliJ/Project/Spring-boot"))){
            Repository repository = git.getRepository();

            //create new branch
            git.branchCreate().setName("test2").call();
            //checkout new branch
            git.checkout().setName("test2").call();

            //back to main brand and delete branch

//            git.checkout().setName("main").call();
//            git.branchDelete().setBranchNames("test2").setForce(true).call();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoFilepatternException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }
}
