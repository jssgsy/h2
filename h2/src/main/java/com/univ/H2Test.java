package com.univ;

import org.junit.Test;

import java.sql.*;

/**
 * created by Univ
 * 16/6/5 12:34
 *
 * 注意不同模式下的url写法
 */
public class H2Test {



    /**
     * 嵌入式模式
     * @throws Exception
     */
    @Test
    public void embeded() throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST ");
        while(rs.next()) {
            System.out.println(rs.getInt("ID")+","+rs.getString("NAME"));
        }
        conn.close();
    }

    /**
     * 服务器模式
     * 注意:此时必须启动服务：%H2_HOME%\bin\h2.bat程序才能正常运行
     * @throws Exception
     */
    @Test
    public void server() throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST ");
        while(rs.next()) {
            System.out.println(rs.getInt("ID")+","+rs.getString("NAME"));
        }
        conn.close();
    }

    /**
     * 混合模式模式
     * 注意:此时表需要在程序中创建
     * @throws Exception
     */
    @Test
    public void mixed() throws Exception {
        Class.forName("org.h2.Driver");
        Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/mem:test", "sa", "");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("CREATE TABLE TEST_MEM(ID INT PRIMARY KEY,NAME VARCHAR(255));");
        stmt.executeUpdate("INSERT INTO TEST_MEM VALUES(1, 'Hello_Mem');");
        ResultSet rs = stmt.executeQuery("SELECT * FROM TEST_MEM");
        while(rs.next()) {
            System.out.println(rs.getInt("ID")+","+rs.getString("NAME"));
        }
        conn.close();
    }

}
