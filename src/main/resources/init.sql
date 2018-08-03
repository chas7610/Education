CREATE TABLE IF NOT EXISTS cookingrecipe(
    id Integer AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
	ingredient VARCHAR(255) NOT NULL,
	author varchar(50) NOT NULL
    );
INSERT INTO
    cookingrecipe(name,ingredient, author)
VALUES
    ('cake', 'layer:250; cream:100; nuts:70', 'I'),
    ('salad', 'potatoes:250; pineapple:100; chicken:50; mayonnaise:10', 'I');