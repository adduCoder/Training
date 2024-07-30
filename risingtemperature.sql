/*
 Rising Temperature
Hard
120/120
Average time to solve is 6m
Problem statement
Table: Weather

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| recordDate    | date    |
| temperature   | int     |
+---------------+---------+
id is the primary key for this table.
This table contains information about the temperature in a certain day.


Write an SQL query to find all dates' id with higher temperature compared to its previous dates (yesterday).

Return the result table in any order.

The query result format is in the following example:
*/

select w1.id as "Id" from weather w1 join weather w2 on w1.recorddate=w2.recorddate+1 
where w1.temperature > w2.temperature;