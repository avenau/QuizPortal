export const getAdmin = () => 
{
    const admin = sessionStorage.getItem('admin');
    if (admin) return JSON.parse(admin);
    else return null;
}

export const getUser = () => 
{
    const user = sessionStorage.getItem('user');
    if (user) return JSON.parse(user);
    else return null;
}

export const getJwtToken = () =>
{
    return sessionStorage.getItem('jwtToken') || null;
}

export const removeUserSession = () => 
{
    sessionStorage.removeItem('jwtToken');
    sessionStorage.removeItem('username');
    sessionStorage.removeItem('accountType');
}

export const setAccountSession = (token, username, accountType) => 
{
    sessionStorage.setItem('jwtToken', token);
    sessionStorage.setItem('username', username);
    sessionStorage.setItem('accountType', accountType);
}


// No logged in people
export const asVisitor = (auth) => {
    if (auth.accountType === null || 
        auth.username === null ||
        auth.jwtToken === null)
        return true;
    return false;
}