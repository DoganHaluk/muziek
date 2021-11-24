INSERT INTO albums(artiestid, gebruikerId, naam, score)
VALUES ((SELECT id FROM artiesten WHERE naam = 'test'), (SELECT id FROM gebruikers WHERE naam = 'test'), 'test', 0);
INSERT INTO tracks(albumId, naam, tijd)
VALUES ((SELECT id FROM albums WHERE naam = 'test'), 'test', CURRENT_TIME);