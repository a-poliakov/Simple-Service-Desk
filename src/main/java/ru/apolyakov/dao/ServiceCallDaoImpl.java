package ru.apolyakov.dao;

import ru.apolyakov.model.ServiceCall;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ServiceCallDaoImpl {
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void save(ServiceCall employee) {
        String query = "insert into ServiceCall (id, name, description) values (?,?,?)";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, employee.getId());
            ps.setString(2, employee.getName());
            ps.setString(3, employee.getDescription());
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Employee saved with id="+employee.getId());
            }else System.out.println("Employee save failed with id="+employee.getId());
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ServiceCall getById(int id) {
        String query = "select name, role from Employee where id = ?";
        ServiceCall emp = null;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                emp = new ServiceCall();
                emp.setId(id);
                emp.setName(rs.getString("name"));
                emp.setDescription(rs.getString("role"));
                System.out.println("Employee Found::"+emp);
            }else{
                System.out.println("No Employee found with id="+id);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return emp;
    }

    public void update(ServiceCall employee) {
        String query = "update Employee set name=?, role=? where id=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setString(1, employee.getName());
            ps.setString(2, employee.getDescription());
            ps.setInt(3, employee.getId());
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("Employee updated with id="+employee.getId());
            }else System.out.println("No Employee found with id="+employee.getId());
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteById(int id) {
        String query = "delete from ServiceCall where id=?";
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            int out = ps.executeUpdate();
            if(out !=0){
                System.out.println("ServiceCall deleted with id="+id);
            }else System.out.println("No ServiceCall found with id="+id);
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<ServiceCall> getAll() {
        String query = "select id, name, description from ServiceCall";
        List<ServiceCall> empList = new ArrayList<ServiceCall>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = dataSource.getConnection();
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                ServiceCall emp = new ServiceCall();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setDescription(rs.getString("description"));
                empList.add(emp);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }finally{
            try {
                rs.close();
                ps.close();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return empList;
    }

}
