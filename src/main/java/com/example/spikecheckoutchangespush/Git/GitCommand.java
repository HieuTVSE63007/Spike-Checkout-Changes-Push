package com.example.spikecheckoutchangespush.Git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.IOException;

public class GitCommand {
    public static void cloneRemoteRepo(String username, String password, String uri, String fileDir)
            throws GitAPIException {

        CredentialsProvider cp = new UsernamePasswordCredentialsProvider(username, password);

        Git.cloneRepository()
                .setURI(uri)
                .setDirectory(new File(fileDir))
                .setCredentialsProvider(cp)
                .call();
    }

    public static void commitToRepo(String fileDir, String addFileCommit, String msgCommit, String branch) {

        try (Git git = Git.open(new File(fileDir))) {
            Repository repository = git.getRepository();

            if (branch != null && !branch.trim().equals("")) {
                git.checkout().setName(branch).call();
            }

            git.add().addFilepattern(addFileCommit).call();
            git.commit().setMessage(msgCommit).call();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoFilepatternException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    public static void pushToRepo(String username, String password, String fileDir) {
        CredentialsProvider cp = new UsernamePasswordCredentialsProvider(username, password);

        try (Git git = Git.open(new File(fileDir))) {
            Repository repository = git.getRepository();

            git.push()
                    .setCredentialsProvider(cp)
                    .call();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoFilepatternException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    public static void commitAndPushToRepo(String username, String password, String fileDir,
                                           String addFileCommit, String msgCommit, String branch) {
        CredentialsProvider cp = new UsernamePasswordCredentialsProvider(username, password);
        try (Git git = Git.open(new File(fileDir))) {
            Repository repository = git.getRepository();

            if (branch != null && !branch.trim().equals("")) {
                git.checkout().setName(branch).call();
            }

            git.add().addFilepattern(addFileCommit).call();
            git.commit().setMessage(msgCommit).call();
            git.push()
                    .setCredentialsProvider(cp)
                    .call();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoFilepatternException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    public static void createBranch(String gitFileDir, String newBranch){
        //get existing repo
        try(Git git = Git.open(new File(gitFileDir))){
            Repository repository = git.getRepository();

            //create new branch
            git.branchCreate().setName(newBranch).call();
            //checkout new branch
            git.checkout().setName(newBranch).call();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoFilepatternException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    public static void deleteBrand(String gitFileDir, String checkoutBranch, String deleteBranch){
        try(Git git = Git.open(new File(gitFileDir))){
            Repository repository = git.getRepository();

            git.checkout().setName(checkoutBranch).call();
            git.branchDelete().setBranchNames(deleteBranch).call();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoFilepatternException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }
}
