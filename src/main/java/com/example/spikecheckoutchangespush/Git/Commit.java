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
import org.eclipse.jgit.revwalk.RevCommit;
import org.eclipse.jgit.transport.CredentialsProvider;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.eclipse.jgit.treewalk.CanonicalTreeParser;
import org.eclipse.jgit.util.io.DisabledOutputStream;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Commit {

    public static void main(String[] args) throws IOException {
        //get existing repo
        String gitFileDir = "D:/IntelliJ/Project/Spring-boot";
        String username = "truongvanhieu291014";
        String password = "c3gvoxnqyv6zotjv3lkbjasz3zmuom7rt7b4gxqof35vgcypo7ka";
        Git git = Git.open(new File(gitFileDir));
        try {
            Repository repository = git.getRepository();

            //get status
            List<DiffEntry> entries = GitCommand.status(gitFileDir);

            //git add and commit
            git.add().addFilepattern(".").call();
            git.commit().setMessage("New commit").call();

            String push = GitCommand.pushToRepo(username, password, gitFileDir);
            if (push != null) {
                System.out.println(push);
            } else {
                System.out.println("Success");
            }

            for (DiffEntry entry : entries) {
                System.out.println("\t" + entry.getChangeType() + ": " + entry.getNewPath());
            }

        } catch (NoFilepatternException e) {
            e.printStackTrace();
        } catch (GitAPIException e) {
            e.printStackTrace();
        } finally {
            git.close();
        }
    }

}
