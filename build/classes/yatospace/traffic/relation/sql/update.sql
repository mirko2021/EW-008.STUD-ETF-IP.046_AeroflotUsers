UPDATE yi_traffic_relations SET rt_word = ?, 
           place = ?, 
           rt_number = ?, 
       	   rt_type = ?, 
       	   rt_subtype = ?, 
       	   relation = ?, 
           income = ?, 
           outcome = ?, 
           direction = ?, 
           location = ?, 
           destination = ?, 
           rt_notes = ? 
WHERE  rt_word = ?; 