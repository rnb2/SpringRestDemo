INSERT INTO public.company(id, name)
VALUES (0, 'Company boss'),
       (1, 'Company child'),
       (2, 'Company animals');

INSERT INTO public.employee(id, email, first_name, last_name, company_id)
VALUES
    (2, 'Lena@gmail.com', 'Lena', 'Budukh', 0),
    (1, 'rnb@gmail.com', 'rnb', 'Budukh', 0),
    (3, 'yarik@gmail.com', 'Yaroslav', 'Budukh', 1),
    (4, 'evangelina@gmail.com', 'Evangelina', 'Budukh', 1),
    (5, 'olivka@gmail.com', 'Olivka', 'Budukh', 2),
    (6, 'Vendi@gmail.com', 'Vendi', 'Budukh', 2);

commit;