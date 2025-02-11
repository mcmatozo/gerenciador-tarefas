package com.gerenciadortarefas.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.gerenciadortarefas.database.DatabaseConnection;
import com.gerenciadortarefas.model.Locale;

public class LocaleDAO {

    private Connection connection;

    public LocaleDAO() throws SQLException {
        this.connection = DatabaseConnection.getConnection();
    }
    
    public void create(Locale locale) {

        String sql = "INSERT INTO localizacoes (name, location, user_id) VALUES (?, ?, ?)";
        
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, locale.getName());
            stmt.setString(2, locale.getLocation());
            stmt.setInt(3, locale.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Locale> list() {
        List<Locale> locales = new ArrayList<>();
        
        String sql = "SELECT * FROM localizacoes";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String location = rs.getString("location");
                int userId = rs.getInt("user_id");
                Locale locale = new Locale(id, userId, name, location);
                locales.add(locale);
                System.out.println(locale);
            } 
            return locales;
        } catch (SQLException e) {

            e.printStackTrace();
            return locales;
        }
    }
}
