### post
curl http://localhost:9000/v1/music -F "url=yahoo.jp"

curl http://localhost:9000/v1/music/1 -X DELETE
curl http://localhost:9000/v1/music -F "id=1" -X DELETE
