CREATE (Invisible_Man:Book {title: 'Invisible Man', language: 'English'})
CREATE (Moby_Dick:Book {title: 'Moby Dick', language: 'English'})
CREATE (Hamlet:Book {title: 'Hamlet', language: 'English'})
CREATE (Ellison:Author {name: 'Ralph Ellison'})
CREATE (Shakespeare:Author {name: 'William Shakespeare'})
CREATE (Melville:Author {name: 'Herman Melville'})

CREATE
(Invisible_Man)-[:AUTHORED]->(Ellison),
(Moby_Dick)-[:AUTHORED]->(Melville),
(Hamlet)-[:AUTHORED]->(Shakespeare)
;