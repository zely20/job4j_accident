SELECT *
FROM accident
         JOIN types
              ON types.id = accident.type_id
         LEFT OUTER JOIN rules_accident
                         ON accident.id = rules_accident.accident_id
         LEFT OUTER JOIN rules
                         ON rules.id = rules_accident.rules_id
where accident.id = 1;