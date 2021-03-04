import groovy.sql.Sql;
import static java.util.Calendar.*


def url = 'jdbc:postgresql://localhost:5432/employee_databse';
def user = 'postgres';
def password = '123456';
def driver = 'org.postgresql.Driver';
def sql = Sql.newInstance(url, user , password , driver);

println ("All Records:");
def query = "select * from employee";
sql.eachRow query , {employee -> println employee.id + " " + employee.name + " " + employee.dob};

def date = new Date();
date.clearTime();
date.set year: 2010, month: 7, date: 10;
def sqlTimestamp = date.toTimestamp();
assert 'java.sql.Timestamp' == sqlTimestamp.class.name;
assert '2010-08-10 00:00:00.0' == sqlTimestamp.toString();

// Create query
query = "INSERT INTO employee (name,dob) VALUES (?,?)";
def name="Ryan";
def dob = sqlTimestamp;
sql.executeInsert query, [name, dob];

//Update query
query = "UPDATE employee SET name=? WHERE id=1";
name="geoegegegge";
sql.executeInsert query, [name];

//Display the updated query
println ("Updated Record:");
query = "select * from employee where name = ?"
sql.eachRow(query,[name], {employee->
    println (employee.id + " "  + employee.name +" " +employee.dob)
});

 //Delete query
query = "delete from employee where name=?";
name = "Ryan";
sql.execute query, [name];

// display delete query
println "After deletion:"
query = "select count(1) from employee where name = ?"
sql.eachRow(query,[name], {row->
    println row[0]
});

sql.close();

