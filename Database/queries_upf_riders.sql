USE upf_riders;

-- Consulta 1
SELECT name
FROM business NATURAL JOIN category
WHERE description LIKE 'Farmacia'
ORDER BY name ASC
; 

-- Consulta 2
SELECT description
FROM category
WHERE description LIKE 'Perruqueria%'
ORDER BY description ASC
;

-- Consulta 3
SELECT b.name, s.distance 
FROM business AS b, headquarters AS h, serve AS s
WHERE distance > 1000 AND h.code = 10 AND b.id = s.business_id AND h.code = s.headquarters_code
ORDER BY distance ASC
;

-- Consulta 4
SELECT vt.description, COUNT(*) AS stocks
FROM vehicle_type vt JOIN vehicle v
ON vt.type_id = v.vehicle_type_id
GROUP BY vt.type_id
ORDER BY stocks DESC
;

-- Consulta 5
SELECT b.name
FROM business b JOIN serve s JOIN headquarters h JOIN rider r
ON b.id = s.business_id AND s.headquarters_code = h.code AND h.code = r.headquarters_code
WHERE r.rider_id = 10
ORDER BY b.name DESC
;

-- Consulta 6
SELECT h.code, h.postal_address, s.distance, b.name
FROM headquarters h JOIN serve s JOIN business b JOIN category cc JOIN category cp
ON h.code = s.headquarters_code AND s.business_id = b.id AND b.category_id = cc.category_id AND cc.parent_category_id = cp.category_id
WHERE cc.description = 'Restaurant' OR cp.description = 'Restaurant'
ORDER BY s.distance ASC
LIMIT 1
;

-- Consulta 7
SELECT cc.description AS children_description , cp.description AS parent_description
FROM category cc JOIN category cp
ON cc.parent_category_id = cp.category_id
WHERE cp.description = 'Tecnologia' OR cp.description = 'Llibreria'
ORDER BY cc.description DESC
;

-- Consulta 8
SELECT l.rider_id, r.name, COUNT(l.vehicle_type_id) AS drives_vehicle_types
FROM rider r JOIN licensed l
ON r.rider_id = l.rider_id
GROUP BY r.rider_id
HAVING COUNT(l.vehicle_type_id) > 3
;

-- Consulta 9 
/* Usamos ANY_VALUE porque en linux nos sale error 1055 porque sql_mode=only_full_group_by */
SELECT b.name, ANY_VALUE(ROUND(b.latitude, 4)) AS latitude, ANY_VALUE(ROUND(b.longitude, 5)) AS longitude, vt.description
FROM business AS b JOIN serve s JOIN uses u JOIN vehicle v JOIN vehicle_type vt
ON b.id = s.business_id AND u.headquarters_code = s.headquarters_code AND v.ref_number = u.vehicle_ref_number AND vt.type_id = v.vehicle_type_id
WHERE u.headquarters_code NOT IN
    (
    SELECT u.headquarters_code
    FROM uses u JOIN vehicle v JOIN vehicle_type vt
    ON u.vehicle_ref_number = v.ref_number AND v.vehicle_type_id = vt.type_id
    WHERE vt.number_of_wheels = 6
    )
AND u.headquarters_code IN
    (
    SELECT u.headquarters_code
    FROM uses u JOIN vehicle v JOIN vehicle_type vt
    ON u.vehicle_ref_number = v.ref_number AND v.vehicle_type_id = vt.type_id
    WHERE vt.number_of_wheels = 2
    )
GROUP BY b.name, vt.description
ORDER BY b.name ASC
;

-- Consulta 10
/* Usamos ANY_VALUE porque en linux nos sale error 1055 porque sql_mode=only_full_group_by */
SELECT b.name, ANY_VALUE(c.description) AS description, ANY_VALUE(v.brand) AS brand, vt.description, ANY_VALUE(vt.number_of_wheels) AS number_of_wheels
FROM category c JOIN business b JOIN serve s JOIN headquarters h JOIN uses u JOIN vehicle v JOIN vehicle_type vt
ON c.category_id = b.category_id AND b.id = s.business_id AND s.headquarters_code = h.code AND h.code = u.headquarters_code AND u.vehicle_ref_number = v.ref_number AND v.vehicle_type_id = vt.type_id
WHERE (c.description LIKE 'Farmacia' OR c.description LIKE 'Dentista') AND (v.brand LIKE 'Mitsubishi' OR v.brand LIKE 'Volkswagen') 
GROUP BY b.name, vt.description
ORDER BY ANY_VALUE(c.description) ASC, ANY_VALUE(vt.number_of_wheels) DESC, b.name ASC, vt.description ASC
;










