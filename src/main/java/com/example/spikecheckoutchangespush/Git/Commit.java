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
        Git git = Git.open(new File(gitFileDir));
        try {
            Repository repository = git.getRepository();

            Status status = git.status().call();
//            System.out.println("Changes not staged for commit:");
//            for (String modified : status.getModified()){
//                System.out.println("\tModified file: " + modified);
//            }
            ObjectReader reader = git.getRepository().newObjectReader();

            CanonicalTreeParser oldTreeIter = new CanonicalTreeParser();
            ObjectId oldTree = git.getRepository().resolve("HEAD~1^{tree}");
            oldTreeIter.reset(reader, oldTree);

            CanonicalTreeParser newTreeIter = new CanonicalTreeParser();
            ObjectId newTree = git.getRepository().resolve("HEAD^{tree}");
            newTreeIter.reset(reader, newTree);

            DiffFormatter diffFormatter = new DiffFormatter(DisabledOutputStream.INSTANCE);
            diffFormatter.setRepository(git.getRepository());
            List<DiffEntry> entries = diffFormatter.scan(oldTreeIter, newTreeIter);

            git.add().addFilepattern(".").call();
            git.commit().setMessage("New commit").call();

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
