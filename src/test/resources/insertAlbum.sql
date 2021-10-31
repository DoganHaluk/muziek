INSERT INTO albums(artiestid, naam, score)
VALUES ((SELECT id FROM artiesten WHERE naam = 'test'), 'test', 0);
INSERT INTO tracks(albumId, naam, tijd)
VALUES ((SELECT id FROM albums WHERE naam = 'test'), 'test', CURRENT_TIME);