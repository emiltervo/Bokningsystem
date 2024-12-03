CREATE TABLE users (
    userID CHAR (10) PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    role TEXT NOT NULL
);
CREATE TABLE doctor (
    userID CHAR (10) PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(userID)
);
CREATE TABLE patient (
    userID CHAR (10) PRIMARY KEY,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    email TEXT NOT NULL,
    FOREIGN KEY (userID) REFERENCES users(userID)
);
CREATE TABLE rooms(
    roomNumber INT PRIMARY KEY,
    roomDoctor CHAR(10) NOT NULL,
    FOREIGN KEY (roomDoctor) REFERENCES doctor(userID)
);
CREATE TABLE appointments(

    date TEXT,
    patuserID CHAR(10),
    docuserID CHAR(10),
    lengthMinutes INT,
    room CHAR(6),
    FOREIGN KEY(patuserID) REFERENCES patient(userID),
    FOREIGN KEY(docuserID) REFERENCES doctor(userID)

);



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
('0205311234', 'John Doe', 'patientPass1', 'john.doe@example.com', 'patient'),
('0304215678', 'Jane Smith', 'patientPass2', 'jane.smith@example.com', 'patient'),
('1208154321', 'Michael Johnson', 'patientPass3', 'michael.johnson@example.com', 'patient'),
('0702198765', 'Emily Davis', 'patientPass4', 'emily.davis@example.com', 'patient'),
('1103056789', 'David Brown', 'patientPass5', 'david.brown@example.com', 'doctor'),
('0607182345', 'Sarah Wilson', 'patientPass6', 'sarah.wilson@example.com', 'doctor'),
('0909123456', 'James Taylor', 'patientPass7', 'james.taylor@example.com', 'doctor'),
('0405167890', 'Jessica Martinez', 'patientPass8', 'jessica.martinez@example.com', 'doctor'),
('0801109876', 'Daniel Anderson', 'patientPass9', 'daniel.anderson@example.com', 'patient'),
('0502134567', 'Laura Thomas', 'patientPass10', 'laura.thomas@example.com', 'patient'),
('0101198765', 'Robert Jackson', 'patientPass11', 'robert.jackson@example.com', 'patient'),
('0303176543', 'Linda White', 'patientPass12', 'linda.white@example.com', 'patient'),
('0704212345', 'William Harris', 'patientPass13', 'william.harris@example.com', 'patient'),
('1205156789', 'Barbara Martin', 'patientPass14', 'barbara.martin@example.com', 'patient'),
('1106198765', 'Richard Thompson', 'patientPass15', 'richard.thompson@example.com', 'patient'),
('0607234567', 'Susan Garcia', 'patientPass16', 'susan.garcia@example.com', 'patient'),
('0908276543', 'Joseph Martinez', 'patientPass17', 'joseph.martinez@example.com', 'patient'),
('0409312345', 'Karen Robinson', 'patientPass18', 'karen.robinson@example.com', 'patient'),
('0801056789', 'Charles Clark', 'patientPass19', 'charles.clark@example.com', 'patient'),
('0502098765', 'Nancy Rodriguez', 'patientPass20', 'nancy.rodriguez@example.com', 'patient'),
('0103134567', 'Thomas Lewis', 'patientPass21', 'thomas.lewis@example.com', 'patient'),
('0304176543', 'Betty Lee', 'patientPass22', 'betty.lee@example.com', 'patient'),
('0705212345', 'Christopher Walker', 'patientPass23', 'christopher.walker@example.com', 'patient'),
('1206256789', 'Sandra Hall', 'patientPass24', 'sandra.hall@example.com', 'patient'),
('1107298765', 'Matthew Allen', 'patientPass25', 'matthew.allen@example.com', 'patient'),
('0608334567', 'Ashley Young', 'patientPass26', 'ashley.young@example.com', 'patient'),
('0909376543', 'Anthony King', 'patientPass27', 'anthony.king@example.com', 'patient'),
('0410412345', 'Donna Wright', 'patientPass28', 'donna.wright@example.com', 'patient'),
('0801456789', 'Mark Scott', 'patientPass29', 'mark.scott@example.com', 'patient'),
('0502498765', 'Patricia Green', 'patientPass30', 'patricia.green@example.com', 'patient'),
('0103534567', 'Steven Adams', 'patientPass31', 'steven.adams@example.com', 'patient'),
('0304576543', 'Deborah Baker', 'patientPass32', 'deborah.baker@example.com', 'patient'),
('0705612345', 'Paul Gonzalez', 'patientPass33', 'paul.gonzalez@example.com', 'patient'),
('1206656789', 'Carol Nelson', 'patientPass34', 'carol.nelson@example.com', 'patient'),
('1107698765', 'Kevin Carter', 'patientPass35', 'kevin.carter@example.com', 'patient'),
('0608734567', 'Michelle Mitchell', 'patientPass36', 'michelle.mitchell@example.com', 'patient'),
('0909776543', 'Brian Perez', 'patientPass37', 'brian.perez@example.com', 'patient'),
('0410812345', 'Dorothy Roberts', 'patientPass38', 'dorothy.roberts@example.com', 'patient'),
('0801856789', 'George Turner', 'patientPass39', 'george.turner@example.com', 'patient'),
('0502898765', 'Lisa Phillips', 'patientPass40', 'lisa.phillips@example.com', 'patient'),
('0103934567', 'Edward Campbell', 'patientPass41', 'edward.campbell@example.com', 'patient'),
('0304976543', 'Karen Parker', 'patientPass42', 'karen.parker@example.com', 'patient'),
('0705012345', 'Donald Evans', 'patientPass43', 'donald.evans@example.com', 'patient'),
('1206056789', 'Helen Edwards', 'patientPass44', 'helen.edwards@example.com', 'patient'),
('1107098765', 'Ronald Collins', 'patientPass45', 'ronald.collins@example.com', 'patient'),
('0608134567', 'Cynthia Stewart', 'patientPass46', 'cynthia.stewart@example.com', 'patient'),
('0909176543', 'Kenneth Sanchez', 'patientPass47', 'kenneth.sanchez@example.com', 'patient'),
('0410212345', 'Margaret Morris', 'patientPass48', 'margaret.morris@example.com', 'patient'),
('0801256789', 'Jason Rogers', 'patientPass49', 'jason.rogers@example.com', 'patient'),
('0502298765', 'Ruth Reed', 'patientPass50', 'ruth.reed@example.com', 'patient'),
('0103334567', 'Jeffrey Cook', 'patientPass51', 'jeffrey.cook@example.com', 'patient'),
('0304376543', 'Maria Morgan', 'patientPass52', 'maria.morgan@example.com', 'patient'),
('0705412345', 'Frank Bell', 'patientPass53', 'frank.bell@example.com', 'patient'),
('1206456789', 'Janet Murphy', 'patientPass54', 'janet.murphy@example.com', 'patient'),
('1107498765', 'Gary Bailey', 'patientPass55', 'gary.bailey@example.com', 'patient'),
('0608534567', 'Pamela Rivera', 'patientPass56', 'pamela.rivera@example.com', 'patient'),
('0909576543', 'Larry Cooper', 'patientPass57', 'larry.cooper@example.com', 'patient'),
('0410612345', 'Katherine Richardson', 'patientPass58', 'katherine.richardson@example.com', 'patient'),
('0801656789', 'Scott Cox', 'patientPass59', 'scott.cox@example.com', 'patient'),
('0502698765', 'Rebecca Howard', 'patientPass60', 'rebecca.howard@example.com', 'patient'),
('0103734567', 'Eric Ward', 'patientPass61', 'eric.ward@example.com', 'patient'),
('0304776543', 'Sharon Torres', 'patientPass62', 'sharon.torres@example.com', 'patient'),
('0705812345', 'Stephen Peterson', 'patientPass63', 'stephen.peterson@example.com', 'patient'),
('1206856789', 'Laura Gray', 'patientPass64', 'laura.gray@example.com', 'patient'),
('1107898765', 'Raymond Ramirez', 'patientPass65', 'raymond.ramirez@example.com', 'patient'),
('0608934567', 'Carolyn James', 'patientPass66', 'carolyn.james@example.com', 'patient'),
('0909976543', 'Jerry Watson', 'patientPass67', 'jerry.watson@example.com', 'patient'),
('0411012345', 'Marie Brooks', 'patientPass68', 'marie.brooks@example.com', 'patient'),
('0802056789', 'Dennis Kelly', 'patientPass69', 'dennis.kelly@example.com', 'patient'),
('0503098765', 'Martha Sanders', 'patientPass70', 'martha.sanders@example.com', 'patient'),
('0104134567', 'Walter Price', 'patientPass71', 'walter.price@example.com', 'patient'),
('0305176543', 'Frances Bennett', 'patientPass72', 'frances.bennett@example.com', 'patient'),
('0706212345', 'Patrick Wood', 'patientPass73', 'patrick.wood@example.com', 'patient'),
('1207256789', 'Gloria Barnes', 'patientPass74', 'gloria.barnes@example.com', 'patient'),
('1108298765', 'Peter Ross', 'patientPass75', 'peter.ross@example.com', 'patient'),
('0609334567', 'Evelyn Henderson', 'patientPass76', 'evelyn.henderson@example.com', 'patient'),
('0910376543', 'Harold Coleman', 'patientPass77', 'harold.coleman@example.com', 'patient'),
('0411412345', 'Jean Jenkins', 'patientPass78', 'jean.jenkins@example.com', 'patient'),
('0802456789', 'Henry Perry', 'patientPass79', 'henry.perry@example.com', 'patient'),
('0503498765', 'Ann Powell', 'patientPass80', 'ann.powell@example.com', 'patient'),
('0104534567', 'Carl Long', 'patientPass81', 'carl.long@example.com', 'patient'),
('0305576543', 'Alice Patterson', 'patientPass82', 'alice.patterson@example.com', 'patient'),
('0706612345', 'Arthur Hughes', 'patientPass83', 'arthur.hughes@example.com', 'patient'),
('1207656789', 'Julie Flores', 'patientPass84', 'julie.flores@example.com', 'patient'),
('1108698765', 'Ryan Washington', 'patientPass85', 'ryan.washington@example.com', 'patient'),
('0609734567', 'Diane Butler', 'patientPass86', 'diane.butler@example.com', 'patient'),
('0910776543', 'Roger Simmons', 'patientPass87', 'roger.simmons@example.com', 'patient'),
('0411812345', 'Kathleen Foster', 'patientPass88', 'kathleen.foster@example.com', 'patient'),
('0802856789', 'Keith Gonzales', 'patientPass89', 'keith.gonzales@example.com', 'patient'),
('0503898765', 'Joyce Bryant', 'patientPass90', 'joyce.bryant@example.com', 'patient'),
('0104934567', 'Lawrence Alexander', 'patientPass91', 'lawrence.alexander@example.com', 'patient'),
('0305976543', 'Judith Russell', 'patientPass92', 'judith.russell@example.com', 'secretary'),
('0707012345', 'Gerald Griffin', 'patientPass93', 'gerald.griffin@example.com', 'secretary'),
('1208056789', 'Megan Diaz', 'patientPass94', 'megan.diaz@example.com', 'secretary'),
('1109098765', 'Albert Hayes', 'patientPass95', 'albert.hayes@example.com', 'patient'),
('0610134567', 'Theresa Myers', 'patientPass96', 'theresa.myers@example.com', 'patient'),
('0911176543', 'Jack Ford', 'patientPass97', 'jack.ford@example.com', 'patient'),
('0412212345', 'Christine Hamilton', 'patientPass98', 'christine.hamilton@example.com', 'patient'),
('0803256789', 'Ralph Graham', 'patientPass99', 'ralph.graham@example.com', 'patient'),
('0504298765', 'Mildred Sullivan', 'patientPass100', 'mildred.sullivan@example.com', 'patient');

