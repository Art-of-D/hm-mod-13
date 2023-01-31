public class UserPostComm {
    private int id;
    private String post;
    private int idC;
    private String comment;

    public UserPostComm(int id, String post, int idC, String comment) {
        this.id = id;
        this.post = post;
        this.idC = idC;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


}
