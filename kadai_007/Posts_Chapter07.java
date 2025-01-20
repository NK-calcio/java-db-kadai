package kadai_007;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Posts_Chapter07 {


	public static void main(String[] args) {
		Connection con = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            // データベースに接続
            con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/challenge_java", 
                "root",                                       
                "xnj7x252nk"                                    
            );
            System.out.println("データベース接続成功：");
            System.out.println( con );
            

            // 5件のレコードを追加
            String insertSql = """
                               INSERT INTO posts (user_id, posted_at, post_content, likes) 
                               VALUES
                               (1003, '2023-02-08', '昨日の夜は徹夜でした・・', 13),
                               (1002, '2023-02-08', 'お疲れ様です！', 12),
                               (1003, '2023-02-09', '今日も頑張ります！', 18),
                               (1001, '2023-02-09', '無理は禁物ですよ！', 17),
                               (1002, '2023-02-10', '明日から連休ですね！', 20);
                               """;

            // SQLクエリを実行
            statement = con.prepareStatement(insertSql);
            int rowCnt = statement.executeUpdate();
            System.out.println("レコード追加を実行します");
            System.out.println(rowCnt + "件のレコードが追加されました");

            //IDが1002の投稿データを検索するSQL
            String selectSql = "SELECT posted_at, post_content, likes FROM posts WHERE user_id = 1002";
            statement = con.prepareStatement(selectSql);
            resultSet = statement.executeQuery();
            
            System.out.println("ユーザーIDが1002のレコードを検索しました");
            int recordCount = 1;
            while (resultSet.next()) {
                String postedAt = resultSet.getString("posted_at");
                String postContent = resultSet.getString("post_content");
                int likes = resultSet.getInt("likes");
                System.out.printf("%d件目：投稿日時=%s／投稿内容=%s／いいね数=%d%n", 
                                  recordCount, postedAt, postContent, likes);
                recordCount++;
            }
        } catch (SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
            // リソースを解放
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (SQLException ignore) {
            }
        }
    }
}
