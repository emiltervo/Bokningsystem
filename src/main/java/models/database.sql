CREATE TABLE users (
    userID BIGINT PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    role TEXT NOT NULL
);
CREATE TABLE doctor (
    userID BIGINT PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(userID)
);
CREATE TABLE patient (
    userID BIGINT PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(userID)
);
CREATE TABLE secretary (
    userID BIGINT PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(userID)
);
CREATE TABLE rooms(
    roomNumber INT PRIMARY KEY,
    roomDoctor BIGINT NOT NULL,
    FOREIGN KEY (roomDoctor) REFERENCES doctor(userID)
);
CREATE TABLE appointments(
    startTime TEXT,
    date TEXT,
    patuserID BIGINT,
    docuserID BIGINT,
    lengthMinutes INT,
    room INT,
    FOREIGN KEY(patuserID) REFERENCES patient(userID),
    FOREIGN KEY(docuserID) REFERENCES doctor(userID)

);
SELECT *
FROM appointments;



CREATE VIEW showDoctor AS
SELECT *
FROM users
WHERE role = 'doctor';

CREATE VIEW showPatient AS
SELECT *
FROM users
WHERE role = 'patient';

CREATE VIEW showSecretary AS
SELECT *
FROM users
WHERE role = 'secretary';

CREATE VIEW showRooms AS
SELECT *
FROM rooms;

INSERT INTO users (userID, name, password, email, role) VALUES
    ('198304218734', 'John Doe', 'patientPass1', 'john.doe@example.com', 'patient'),
    ('199003215678', 'Jane Smith', 'patientPass2', 'jane.smith@example.com', 'patient'),
    ('198512158432', 'Michael Johnson', 'patientPass3', 'michael.johnson@example.com', 'patient'),
    ('197002198765', 'Emily Davis', 'patientPass4', 'emily.davis@example.com', 'patient'),
    ('198711056789', 'David Brown', 'patientPass5', 'david.brown@example.com', 'doctor'),
    ('197607182345', 'Sarah Wilson', 'patientPass6', 'sarah.wilson@example.com', 'doctor'),
    ('198909123456', 'James Taylor', 'patientPass7', 'james.taylor@example.com', 'doctor'),
    ('198404167890', 'Jessica Martinez', 'patientPass8', 'jessica.martinez@example.com', 'doctor'),
    ('198008109876', 'Daniel Anderson', 'patientPass9', 'daniel.anderson@example.com', 'patient'),
    ('197505134567', 'Laura Thomas', 'patientPass10', 'laura.thomas@example.com', 'patient'),
    ('196501198765', 'Robert Jackson', 'patientPass11', 'robert.jackson@example.com', 'patient'),
    ('197703166543', 'Linda White', 'patientPass12', 'linda.white@example.com', 'patient'),
    ('198707242345', 'William Harris', 'patientPass13', 'william.harris@example.com', 'patient'),
    ('198512156789', 'Barbara Martin', 'patientPass14', 'barbara.martin@example.com', 'patient'),
    ('198611198765', 'Richard Thompson', 'patientPass15', 'richard.thompson@example.com', 'patient'),
    ('197606234567', 'Susan Garcia', 'patientPass16', 'susan.garcia@example.com', 'patient'),
    ('198909276543', 'Joseph Martinez', 'patientPass17', 'joseph.martinez@example.com', 'patient'),
    ('198404312345', 'Karen Robinson', 'patientPass18', 'karen.robinson@example.com', 'patient'),
    ('198008056789', 'Charles Clark', 'patientPass19', 'charles.clark@example.com', 'patient'),
    ('197505098765', 'Nancy Rodriguez', 'patientPass20', 'nancy.rodriguez@example.com', 'patient'),
    ('196501134567', 'Thomas Lewis', 'patientPass21', 'thomas.lewis@example.com', 'patient'),
    ('197703176543', 'Betty Lee', 'patientPass22', 'betty.lee@example.com', 'patient'),
    ('198707212345', 'Christopher Walker', 'patientPass23', 'christopher.walker@example.com', 'patient'),
    ('198512256789', 'Sandra Hall', 'patientPass24', 'sandra.hall@example.com', 'patient'),
    ('198611298765', 'Matthew Allen', 'patientPass25', 'matthew.allen@example.com', 'patient'),
    ('197606334567', 'Ashley Young', 'patientPass26', 'ashley.young@example.com', 'patient'),
    ('198909376543', 'Anthony King', 'patientPass27', 'anthony.king@example.com', 'patient'),
    ('198404412345', 'Donna Wright', 'patientPass28', 'donna.wright@example.com', 'patient'),
    ('198008145678', 'Mark Scott', 'patientPass29', 'mark.scott@example.com', 'patient'),
    ('197505298765', 'Patricia Green', 'patientPass30', 'patricia.green@example.com', 'patient'),
    ('196501334567', 'Steven Adams', 'patientPass31', 'steven.adams@example.com', 'patient'),
    ('197703276543', 'Deborah Baker', 'patientPass32', 'deborah.baker@example.com', 'patient'),
    ('198707312345', 'Paul Gonzalez', 'patientPass33', 'paul.gonzalez@example.com', 'patient'),
    ('198512356789', 'Carol Nelson', 'patientPass34', 'carol.nelson@example.com', 'patient'),
    ('198611398765', 'Kevin Carter', 'patientPass35', 'kevin.carter@example.com', 'patient'),
    ('197606434567', 'Michelle Mitchell', 'patientPass36', 'michelle.mitchell@example.com', 'patient'),
    ('198909476543', 'Brian Perez', 'patientPass37', 'brian.perez@example.com', 'patient'),
    ('198404512345', 'Dorothy Roberts', 'patientPass38', 'dorothy.roberts@example.com', 'patient'),
    ('198008185678', 'George Turner', 'patientPass39', 'george.turner@example.com', 'patient'),
    ('197505398765', 'Lisa Phillips', 'patientPass40', 'lisa.phillips@example.com', 'patient'),
    ('196501434567', 'Edward Campbell', 'patientPass41', 'edward.campbell@example.com', 'patient'),
    ('197703376543', 'Karen Parker', 'patientPass42', 'karen.parker@example.com', 'patient'),
    ('198707412345', 'Donald Evans', 'patientPass43', 'donald.evans@example.com', 'patient'),
    ('198512456789', 'Helen Edwards', 'patientPass44', 'helen.edwards@example.com', 'patient'),
    ('198611498765', 'Ronald Collins', 'patientPass45', 'ronald.collins@example.com', 'patient'),
    ('197606534567', 'Cynthia Stewart', 'patientPass46', 'cynthia.stewart@example.com', 'patient'),
    ('198909576543', 'Kenneth Sanchez', 'patientPass47', 'kenneth.sanchez@example.com', 'patient'),
    ('198404612345', 'Margaret Morris', 'patientPass48', 'margaret.morris@example.com', 'patient'),
    ('198008225678', 'Jason Rogers', 'patientPass49', 'jason.rogers@example.com', 'patient'),
    ('197505498765', 'Ruth Reed', 'patientPass50', 'ruth.reed@example.com', 'patient'),
    ('196501534567', 'Jeffrey Cook', 'patientPass51', 'jeffrey.cook@example.com', 'patient'),
    ('197703476543', 'Maria Morgan', 'patientPass52', 'maria.morgan@example.com', 'patient'),
    ('198707512345', 'Frank Bell', 'patientPass53', 'frank.bell@example.com', 'patient'),
    ('198512556789', 'Janet Murphy', 'patientPass54', 'janet.murphy@example.com', 'patient'),
    ('198611598765', 'Gary Bailey', 'patientPass55', 'gary.bailey@example.com', 'patient'),
    ('197606634567', 'Pamela Rivera', 'patientPass56', 'pamela.rivera@example.com', 'patient'),
    ('198909676543', 'Larry Cooper', 'patientPass57', 'larry.cooper@example.com', 'patient'),
    ('198404712345', 'Katherine Richardson', 'patientPass58', 'katherine.richardson@example.com', 'patient'),
    ('198008265678', 'Scott Cox', 'patientPass59', 'scott.cox@example.com', 'patient'),
    ('197505598765', 'Rebecca Howard', 'patientPass60', 'rebecca.howard@example.com', 'patient'),
    ('196501634567', 'Eric Ward', 'patientPass61', 'eric.ward@example.com', 'patient'),
    ('197703576543', 'Sharon Torres', 'patientPass62', 'sharon.torres@example.com', 'patient'),
    ('198707612345', 'Stephen Peterson', 'patientPass63', 'stephen.peterson@example.com', 'patient'),
    ('198512656789', 'Laura Gray', 'patientPass64', 'laura.gray@example.com', 'patient'),
    ('198611698765', 'Raymond Ramirez', 'patientPass65', 'raymond.ramirez@example.com', 'patient'),
    ('197606734567', 'Carolyn James', 'patientPass66', 'carolyn.james@example.com', 'patient'),
    ('198909776543', 'Jerry Watson', 'patientPass67', 'jerry.watson@example.com', 'patient'),
    ('198404812345', 'Marie Brooks', 'patientPass68', 'marie.brooks@example.com', 'patient'),
    ('198008305678', 'Dennis Kelly', 'patientPass69', 'dennis.kelly@example.com', 'patient'),
    ('197505698765', 'Martha Sanders', 'patientPass70', 'martha.sanders@example.com', 'patient'),
    ('196501734567', 'Walter Price', 'patientPass71', 'walter.price@example.com', 'patient'),
    ('197703676543', 'Frances Bennett', 'patientPass72', 'frances.bennett@example.com', 'patient'),
    ('198707712345', 'Patrick Wood', 'patientPass73', 'patrick.wood@example.com', 'patient'),
    ('198512756789', 'Gloria Barnes', 'patientPass74', 'gloria.barnes@example.com', 'patient'),
    ('198611798765', 'Peter Ross', 'patientPass75', 'peter.ross@example.com', 'patient'),
    ('197606834567', 'Evelyn Henderson', 'patientPass76', 'evelyn.henderson@example.com', 'patient'),
    ('198909876543', 'Harold Coleman', 'patientPass77', 'harold.coleman@example.com', 'patient'),
    ('198404912345', 'Jean Jenkins', 'patientPass78', 'jean.jenkins@example.com', 'patient'),
    ('198008345678', 'Henry Perry', 'patientPass79', 'henry.perry@example.com', 'patient'),
    ('197505798765', 'Ann Powell', 'patientPass80', 'ann.powell@example.com', 'patient'),
    ('196501834567', 'Carl Long', 'patientPass81', 'carl.long@example.com', 'patient'),
    ('197703776543', 'Alice Patterson', 'patientPass82', 'alice.patterson@example.com', 'patient'),
    ('198707812345', 'Arthur Hughes', 'patientPass83', 'arthur.hughes@example.com', 'patient'),
    ('198512856789', 'Julie Flores', 'patientPass84', 'julie.flores@example.com', 'patient'),
    ('198611898765', 'Ryan Washington', 'patientPass85', 'ryan.washington@example.com', 'patient'),
    ('197606934567', 'Diane Butler', 'patientPass86', 'diane.butler@example.com', 'patient'),
    ('198909976543', 'Roger Simmons', 'patientPass87', 'roger.simmons@example.com', 'patient'),
    ('198405012345', 'Kathleen Foster', 'patientPass88', 'kathleen.foster@example.com', 'patient'),
    ('198008385678', 'Keith Gonzales', 'patientPass89', 'keith.gonzales@example.com', 'patient'),
    ('197505898765', 'Joyce Bryant', 'patientPass90', 'joyce.bryant@example.com', 'patient'),
    ('196501934567', 'Lawrence Alexander', 'patientPass91', 'lawrence.alexander@example.com', 'patient'),
    ('197703876543', 'Judith Russell', 'patientPass92', 'judith.russell@example.com', 'secretary'),
    ('198707912345', 'Gerald Griffin', 'patientPass93', 'gerald.griffin@example.com', 'secretary'),
    ('198512956789', 'Megan Diaz', 'patientPass94', 'megan.diaz@example.com', 'secretary'),
    ('198611998765', 'Albert Hayes', 'patientPass95', 'albert.hayes@example.com', 'patient'),
    ('197607034567', 'Theresa Myers', 'patientPass96', 'theresa.myers@example.com', 'patient'),
    ('198910076543', 'Jack Ford', 'patientPass97', 'jack.ford@example.com', 'patient'),
    ('198405112345', 'Christine Hamilton', 'patientPass98', 'christine.hamilton@example.com', 'patient'),
    ('198008425678', 'Ralph Graham', 'patientPass99', 'ralph.graham@example.com', 'patient'),
    ('197506098765', 'Mildred Sullivan', 'patientPass100', 'mildred.sullivan@example.com', 'patient');
INSERT INTO patient (userID, name, password, email)
SELECT userID, name, password, email
FROM users
WHERE role = 'patient';

INSERT INTO doctor (userID, name, password, email)
SELECT userID, name, password, email
FROM users
WHERE role = 'doctor';

INSERT INTO secretary (userID, name, password, email)
SELECT userID, name, password, email
FROM users
WHERE role = 'secretary';

CREATE TABLE recipes (
     recipeID SERIAL PRIMARY KEY,
     userID BIGINT NOT NULL,
     content TEXT NOT NULL,
     FOREIGN KEY (userID) REFERENCES patient(userID)
);

INSERT INTO recipes (userID, content)
SELECT userID, 'Prescription for ' || name
FROM patient;



