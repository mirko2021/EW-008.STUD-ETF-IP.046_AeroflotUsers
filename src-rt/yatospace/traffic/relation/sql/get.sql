SELECT rt_word, place, rt_number, 
       rt_type, rt_subtype, relation, 
       income, outcome, direction, 
       location, destination, rt_notes FROM yi_traffic_relations
WHERE  rt_word = ?; 