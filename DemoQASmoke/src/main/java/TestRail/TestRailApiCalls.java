package TestRail;

import org.json.simple.JSONObject;

import java.util.Map;

public class TestRailApiCalls {

    public static Object addResultToCase(String runId, String testCaseId, Map<String, Object> data) {
        try {
            return TestRailApiManager.getInstance().sendPost("add_result_for_case/" + runId + "/" + testCaseId, data);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(testCaseId + " case id possible not found");
            return null;
        }
    }

    public static String addTestRun(String testRailProjectId, Map<String, Object> data) {
        try {
            JSONObject testRailRun = (JSONObject) TestRailApiManager.getInstance().sendPost("add_run/" + testRailProjectId, data);
            String testRailRunId = String.valueOf(testRailRun.get("id"));
            return testRailRunId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
