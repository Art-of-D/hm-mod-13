import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.util.*;

public class HttpTest {
    private static final String USER_URI = "https://jsonplaceholder.typicode.com/users";
    private static final String USERPOST_URI = "https://jsonplaceholder.typicode.com/users/1/posts";
    private static final String USERCOMM_URI = "https://jsonplaceholder.typicode.com/posts/10/comments";
    private static final String USERTASK_URI = "https://jsonplaceholder.typicode.com/users/1/todos";
    private static final Gson GSON = new Gson();
    private static final String PATH = "/Users/art_of_d/Java/hm-mod-13/src/test/java";


    public static void main(String[] args) throws Exception {
        //creating a list of user with info
        List<UserJsonFilter> list;
        try {
            list = HttpFunction.getList(USER_URI);
            System.out.println("List created");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //POST
        UserJsonFilter newUser = new UserJsonFilter(HttpUtil.maxOfHttml(list)+1, "Warner Bro",
                "coconut@gmail.com", new UserJsonFilter.UserAdress("Douglas Extension",
                "Suite 847", "NY", "59590-4157",
                new UserJsonFilter.UserAdress.GeoUserAdress("-99.1111","11.0001")),
                "1-463-123-4447", "ukraine.info", new UserJsonFilter.Company("Tommy-Tommy",
                "Hummer hummer the world","strategic applications to world"));
        try {
            int ans = HttpFunction.post(USER_URI, newUser);
            if (ans >= 200 && ans <= 204){
                System.out.println("New info sent! " + "Status code - " + ans);
            } else {
                System.out.println("You need to check - " + ans);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //PUT
        UserJsonFilter userPut = new UserJsonFilter(3, "Dr. Evil",
                "peach@gmail.com", new UserJsonFilter.UserAdress("De & Co",
                "apt. 111", "London", "666999-000",
                new UserJsonFilter.UserAdress.GeoUserAdress("-99.1111","11.0001")),
                "1-463-123-4447", "ld.info", new UserJsonFilter.Company("Boom",
                "Change the world","strategic applications to world"));
        int ans2 = HttpFunction.put(USER_URI, userPut, 3);
        if (ans2 >= 200 && ans2 <= 204){
            System.out.println("New info sent! " + "Status code - " + ans2);
        } else {
            System.out.println("You need to check - " + ans2);
        }

        //DELETE

        //For another implementation
        // List <UserJsonFilter> byID = searchById(list,2);
        //int ans2 = HttpFunction.delete(USER_URI, newUser); don`t work why?

        int ans3 = HttpFunction.delete(USER_URI, 4);
        if (ans3 >= 200 && ans3 <= 204){
            System.out.println("Delete successful sent! " + "Status code - " + ans3);
        } else {
            System.out.println("You need to check - " + ans3);
        }

        //Get info
        HttpFunction.get(USER_URI);

        //Get info about
        final List<UserJsonFilter> user = HttpFunction.getList(String.valueOf(URI.create(String.format("%s?id=%d", USER_URI, list.get(1).getId()))));
        System.out.println(GSON.toJson(user, new TypeToken<List<UserJsonFilter>>(){}.getType()));

        //ANOTHER
        // System.out.println(GSON.toJson(searchById(list, 1)));

        //Get info by name
        System.out.println(GSON.toJson(HttpUtil.searchByName(list, "Clementina DuBuque")));

        //Get last post and get last comment
        List<UserPostFilter> postList = HttpUtilPost.getList(USERPOST_URI);
        List<UserCommFilter> commList = HttpComm.getList(USERCOMM_URI);
        UserPostComm userPostComms = new UserPostComm(HttpUtil.maxId(postList),HttpUtil.searchLastPost(postList,
                HttpUtil.maxId(postList)), HttpUtil.maxIdComm(commList), HttpUtil.searchLastComm(commList,
                HttpUtil.maxIdComm(commList)));
        HttpUtil.fileWriterJson(userPostComms, PATH);

        //Get all open tasks
        List<UserTodos> listOfTask = HttpTasks.getList(USERTASK_URI);
        System.out.println(GSON.toJson(HttpUtil.opendTasks(listOfTask)));


    }



}
