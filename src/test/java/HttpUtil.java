import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class HttpUtil {
    //Create json file
    static void fileWriterJson(UserPostComm userPostComms, String PATH) throws IOException {
        String gsonStr = new Gson().toJson(userPostComms);

        File fileJS = new File(PATH + "/user-" + userPostComms.getId() + "-post-" + userPostComms.getIdC() + "-comments.json");
        if (!fileJS.exists()){
            fileJS.createNewFile();
        }
        try(FileWriter fileWriter = new FileWriter(PATH + "/user-" + userPostComms.getId() + "-post-" + userPostComms.getIdC() + "-comments.json")){
            fileWriter.write(gsonStr);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //Get the max element
    static Integer maxOfHttml(List<UserJsonFilter> list){
        Integer max = list
                .stream()
                .map(UserJsonFilter::getId)
                .max(Integer::compareTo)
                .get();
        return max;
    }

    /*For another implementation
    private static List<UserJsonFilter> searchById(List<UserJsonFilter> list, int number){
        List<UserJsonFilter> byId = list
                .stream()
                .filter(userJsonFilter -> userJsonFilter.getId() == number)
                .collect(Collectors.toList());
        return byId;
    }*/
    static List<UserJsonFilter> searchByName(List<UserJsonFilter> list, String name){
        List<UserJsonFilter> byName = list
                .stream()
                .filter(userJsonFilter -> userJsonFilter.getName().equals(name))
                .collect(Collectors.toList());

        return byName;
    }


    static int maxId(List<UserPostFilter> listPost){
        int maxId = listPost
                .stream()
                .map(UserPostFilter::getId)
                .max(Integer::compareTo)
                .get();
        return maxId;
    }

    static String searchLastPost(List<UserPostFilter> listPost, int maxId){
        String lastPost = listPost
                .stream()
                .filter(userPostFilter -> userPostFilter.getId() == maxId)
                .map(userPostFilter -> userPostFilter.getBody())
                .collect(Collectors.joining(""));

        return lastPost;
    }
    static int maxIdComm(List<UserCommFilter> listPost) {
        int maxIdComm = listPost
                .stream()
                .map(UserCommFilter::getId)
                .max(Integer::compareTo)
                .get();
        return maxIdComm;
    }

    static String searchLastComm(List<UserCommFilter> commList, int maxIdComm){
        String lastPost = commList
                .stream()
                .filter(userCommFilter -> userCommFilter.getId() == maxIdComm)
                .map(userCommFilter -> userCommFilter.getBody())
                .collect(Collectors.joining(""));
        return lastPost;
    }

    static List<UserTodos> opendTasks(List<UserTodos> listOfTask){
        List<UserTodos>  openTask = listOfTask
                .stream()
                .filter(userTodos -> !userTodos.isCompleted())
                .collect(Collectors.toList());
        return openTask;
    }
}
