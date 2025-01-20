package kadai_010;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Scores_Chapter10 {
	public static void main(String[] args) {
		Connection con = null;
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
        	
        	
        	
        	
        	//データベースへ接続
        	con = DriverManager.getConnection(
        			"jdbc:mysql://localhost:3306/challenge_java", 
                    "root",                                       
                    "xnj7x252nk"
        );
        	 System.out.println("データベース接続成功");
        	 System.out.println(con);
        	 
        	 //クエリ準備
        	 statement = con.createStatement();
        	 String updatesql = "UPDATE scores SET score_math = 95, score_english = 80 WHERE id = 5;";
        	 statement = con.createStatement();
        	 System.out.println("レコード更新を実行します");
        	 int updated = statement.executeUpdate(updatesql);
        	 System.out.println(updated + "件のレコードが更新されました");
        	 
        	 //データの並べ替え
        	 String selectsql = "SELECT id, name, score_math, score_english FROM scores ORDER BY score_math DESC, score_english DESC;";
        	 resultSet = statement.executeQuery(selectsql);
        	 System.out.println("数学・英語の点数が高い順に並べ替えました");
        	 
        	 //結果を表示
        	 int record = 1;
        	 while (resultSet.next()) {
        		 int id = resultSet.getInt("id");
        		 String name = resultSet.getString("name");
        		 int score_math = resultSet.getInt("score_math");
        		 int score_english = resultSet.getInt("score_english");
				System.out.println(resultSet.getRow() + "件目：生徒ID=" + id + "／氏名=" + name + "／数学=" + score_math + "／英語=" + score_english );
        	 }
        } catch (SQLException e) {
            System.out.println("エラー発生：" + e.getMessage());
        } finally {
            // リソースの解放
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (SQLException ignore) {
            }
        }
    }
        	 

	}


