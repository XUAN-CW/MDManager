

import java.util.List;

/**
 * @author XUAN
 * @date 2021/4/10 - 15:08
 * @references
 * @purpose
 * @errors
 */
public class Article {
    String title;
    List<String> categories;
    String date;
    String toYAML(){
        String yaml="title: "+title+"\n";
        yaml+="date: "+date+"\n";
        yaml+="tags: "+"\n";
        yaml+="categories: \n";
        for (int i = 0; i < categories.size(); i++) {
            yaml+="- "+getCategories().get(i)+"\n";
        }
        return yaml;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", categories=" + categories +
                ", date='" + date + '\'' +
                '}';
    }
}
