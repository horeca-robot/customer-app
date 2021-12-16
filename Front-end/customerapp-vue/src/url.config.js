const serverVars = {
    apiUrl: 'https://rigscustomerapi.azurewebsites.net',
  };
  
  const localVars = {
    apiUrl: 'http://localhost:8080',
  };
  
  export function getConfiguration() {
    if (process.env.NODE_ENV === 'production') {
      return serverVars;
    }
  
    return localVars;
  }