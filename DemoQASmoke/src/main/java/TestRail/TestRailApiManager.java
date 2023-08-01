package TestRail;

public class TestRailApiManager {
    private static APIClient testRailApiClient;

    public static APIClient getInstance() {
        if (testRailApiClient == null)
            createInstance();
        return testRailApiClient;
    }

    private static void createInstance() {
        testRailApiClient = new APIClient("https://jostens.testrail.io/");
        testRailApiClient.setUser("khrushch+automation@qarea.us"); //should be automation user here
        testRailApiClient.setPassword("Ledzeppelin1988!");
    }
}
