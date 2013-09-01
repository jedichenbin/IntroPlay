void doGet(HttpServletRequest req, HttpServletResponse res) {
  // Apache HttpClient
  HttpClient client = new HttpClient();
  GetMethod method = new GetMethod("www.foobar.com");

  // blocking until return from synchronous call
  int status = client.executeMethod(method);
  LOG.log("Response status code is {}", status);
}


public Result index(String url) {
  F.Promise<WS.Response> responsePromise = WS.url(url).get();
  return async(responsePromise.map(new F.Function<WS.Response, Result>() {
    @Override
    public Result apply(WS.Response response) throws Throwable {
      return ok(response.getBody()).as("text/html");
    }
  }));
}


// make two parallel async calls
val fooFuture = WS.url(url1).get()
val barFuture = WS.url(url2).get()

for {
  foo <- fooFuture
  bar <- barFuture
} yield {
  Ok(...)
}

// make two sequential async calls
for {
  foo <- WS.url(url1).get()
  bar <- WS.url(buildUrl(foo)).get()
} yield {
  Ok(...)
}



