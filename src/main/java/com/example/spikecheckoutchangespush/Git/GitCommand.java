package com.example.spikecheckoutchangespush.Git;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.Status;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.api.errors.NoFilepatternException;
import org.eclipse.jgit.diff.DiffEntry;
import org.eclipse.jgit.diff.DiffFormatter;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.ObjectReader;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.util.io.DisabledOutputStream;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

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

    public static List<DiffEntry> status(String gitFileDir) throws IOException {
        Git git = Git.open(new File(gitFileDir));
        List<DiffEntry> entries;
        try {
            ObjectReader reader = git.getRepository().newObjectReader();

            CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
            ObjectId oldTree = git.getRepository().resolve("HEAD~1^{tree}");
            oldTreeIter.reset(reader, oldTree);

            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
            ObjectId newTree = git.getRepository().resolve("HEAD^{tree}");
            newTreeIter.reset(reader, newTree);

            DiffFormatter diffFormatter = new DiffFormatter(DisabledOutputStream.INSTANCE);
            diffFormatter.setRepository(git.getRepository());
            entries = diffFormatter.scan(oldTreeIter, newTreeIter);

        } finally {
            git.close();
        }
        return entries;
    }

    public static void commitToRepo(String gitFileDir, String addFileDirToCommit, String msgCommit) {

        try (Git git = Git.open(new File(gitFileDir))) {
            Repository repository = git.getRepository();

            git.add().addFilepattern(addFileDirToCommit).call();
            git.commit().setMessage(msgCommit).call();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoFilepatternException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }

    public static String pushToRepo(String username, String password, String fileDir) {
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
            return "Not Authorize";
        }
        return null;
    }

    public static void commitAndPushToRepo(String username, String password, String gitFileDir,
                                           String addFileDirToCommit, String msgCommit) {
        CredentialsProvider cp = new UsernamePasswordCredentialsProvider(username, password);
        try (Git git = Git.open(new File(gitFileDir))) {
            Repository repository = git.getRepository();

            git.add().addFilepattern(addFileDirToCommit).call();
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

    public static void createBranch(String gitFileDir, String newBranch) {
        //get existing repo
        try (Git git = Git.open(new File(gitFileDir))) {
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

    public static void deleteBrand(String gitFileDir, String checkoutBranch, String deleteBranch) {
        try (Git git = Git.open(new File(gitFileDir))) {
            Repository repository = git.getRepository();

            git.checkout().setName(checkoutBranch).call();
//            git.branchDelete().setBranchNames(deleteBranch).call();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoFilepatternException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        }
    }
}
