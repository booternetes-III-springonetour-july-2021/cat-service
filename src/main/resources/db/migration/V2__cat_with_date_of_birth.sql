alter table cat
add column date_of_birth date;

update cat
set date_of_birth = current_date - interval '1 month' * age_in_months;

alter table cat
drop column age_in_months;
