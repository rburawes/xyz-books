const formatter = new Intl.NumberFormat('en-US', {
    style: 'currency',
    currency: 'USD',
    minimumFractionDigits: 2
})

$(document).ready(function () {
    $("#search-form").submit(function (event) {
        event.preventDefault(); // manually submit form
        try {
            submit_request();
        } catch (err) {
            console.log(err);
            $("#result").find("tr:not(:first)").remove();
            $("#error").html(err.message);
            $("#error").show();
        }
    });
});

function submit_request() {
    let isbn = $("#isbn").val().replace(/-/g,"");
    // if not valid then it will not call the API
    console.log("ISBN value: " + isbn);
    validate_isbn(isbn);
    $("#isbn-search").prop("disabled", true);
    $("#error").hide();
    $("#result").find("tr:not(:first)").remove();
    $.ajax({
        type: "GET",
        contentType: "application/json",
        url: "api/v1/books/isbn/" + isbn,
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            let tableData = "<tr>\n" +
                "<th scope=\"row\"><img src='" + data.imageUrl + "' class='img-thumbnail'/></th>\n" +
                "<td>" + data.title + "</td>\n" +
                "<td>" + data.authors + "</td>\n" +
                "<td>" + data.isbn + "</td>\n" +
                "<td>" + formatter.format(data.price) + "</td>\n" +
                "<td>" + data.publicationYear + "</td>\n" +
                "<td>" + data.publisherName + "</td>\n" +
                "<td>" + data.edition + "</td>\n" +
                "</tr>"
            $('#result').append(tableData);
            console.log("SUCCESS : ", data);
            $("#isbn-search").prop("disabled", false);
        },
        error: function (e) {
            console.log("Error: ", e);
            $("#error").show();
            if (e.status === 400) {
                $('#error').html("Invalid ISBN");
            } else if (e.status === 404) {
                $('#error').html("ISBN not found");
            } else {
                $('#error').html("Unable to execute search request.");
            }
            $("#isbn-search").prop("disabled", false);
        }
    });
}

/**
 * Validation using regex.
 * @param isbn
 */
function validate_isbn(isbn) {
    let pattern = /^(?=(?:\D*\d){10}(?:(?:\D*\d){3})?$)[\d-]+$/i;
    let result = isbn.match(pattern);
    console.log("Valid: " + result);
    if (!result) {
        throw new Error("Invalid ISBN");
    }
}

