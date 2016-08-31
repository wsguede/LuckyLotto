# LuckyLotto

clone locally
```
git clone https://github.com/wsguede/LuckyLotto
```

then run 
```
gradlew bootRun
```
---

Submit POST http://localhost:8080/lotto

```javascript
Request
{
  "Bill": 15,
  "Phil": 10,
  "Tyler": 8,
  "John": 6
}
```

```javascript
Response
{
  "1": "Bill",
  "2": "John",
  "3": "Phil",
  "4": "Tyler"
}
```
 URL param | default | description |
 --- | --- | --- |
 clean | false | if true, removes all winner tickets after win |
 num | 10 | number of winners |
 multipleWins | false | if true, allow player to win multiple times |
