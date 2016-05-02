<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <title><g:message code="springSecurity.denied.title" /></title>
        <script type="text/javascript">
            setTimeout(function () {
                window.location.href = '${request.getContextPath()}';
            }, 5000); //will call the function after 2 secs.
        </script>

    </head>

    <body>
        <div class='errors'><g:message code="springSecurity.denied.message" /></div>
    </body>
</html>