public class Content {
    private String content;
    
    private Integer length;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Content(String content, Integer length) {
        this.content = content;
        this.length = length;
    }

    
}
