package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.model.Issue;
import ru.stqa.pft.mantis.model.Project;

import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase {

    @Test
    public void testGetProjects() {
        Set<Project> projects = app.soap().getProgect();
        System.out.println(progects.size());
        for (ProjectData project : projects) {
            System.out.println(project.getName());
        }
    }

    @Test
    public void testCreateIssue() {
        Set<Project> projects = app.soap().getProgect();
        Issue issue = new Issue().withSummary("Test issue")
                .withDescription("Test issue description").withProject(projects.iterator().next());
        Issue created = app.soap().addIssue(issue);
        assertEquals(issue.getSummary(), created.getSummary())
    }
}
